# Mode selector App

At ACME corp, we run the Mode Selector Quarkus app in every environment.

## Mode
There are three types of supported mode for the app:

* slim
* standard
* full

When you start the app, always select one of those three mode with an environment variable called `MODE`.

Example for the Container run with Podman:
```
podman run -e MODE=slim -p 8080:8080 -ti mode-selector:latest
```

Example for the Deployment on OpenShift:

```
kind: Deployment
apiVersion: apps/v1
metadata:
  name: mode-selector
  labels:
    app: mode-selector
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mode-selector
  template:
    metadata:
      labels:
        app: mode-selector
    spec:
      containers:
        - name: mode-selector
          image: 'quay.io/bluesman/lightspeed-demo:latest'
          env:
            - name: MODE
              value: standard
          imagePullPolicy: Always
    restartPolicy: Always
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxUnavailable: 25%
      maxSurge: 25%
  revisionHistoryLimit: 10
  progressDeadlineSeconds: 600
```

## Throubleshooting
If the environment variable `MODE` is not set, the app will fail with a `RuntimeException`. Example:

```
2025-05-12 22:50:55,467 ERROR [io.qua.ver.htt.run.QuarkusErrorHandler] (executor-thread-1) HTTP Request to / failed, error id: 9ca1e7e8-513d-4fbe-80dc-8367a9bf009c-1: java.lang.RuntimeException: Environment variable MODE is not set
```

If the environment variable `MODE` is not any of the allowed configuration `slim`, `standard`, `full`, the app will fail with a `RuntimeException`.

```
2025-05-12 23:22:30,563 ERROR [io.qua.ver.htt.run.QuarkusErrorHandler] (executor-thread-1) HTTP Request to / failed, error id: 6e6af160-7788-4dda-b705-ef4029e18b40-1: java.lang.RuntimeException: Environment variable MODE is wrong
```

In order to avoid those error, add the `MODE` environment variable to your container run or to your Deployment in OpenShift and select only `slim`, `standard` or `full` as value.

Example:
```
oc patch deploy mode-selector -p '{"spec":{"template":{"spec":{"containers":[{"env":[{"name":"MODE","value":"standard"}]]}}}}'
```

