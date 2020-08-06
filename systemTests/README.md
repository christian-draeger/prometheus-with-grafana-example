E2E-Tests
=========

During a test run all components will be started via docker-compose.

#### Run all e2e tests (with chrome as default browser)
```bash
./gradlew build
```

#### Run with different browser 
#####(supported options: chrome, chrome-headless, firefox, firefox-headless, opera, safari, edge, ie)
```bash
./gradlew build -Dbrowser=firefox
```
