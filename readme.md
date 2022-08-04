# Steps to setup CAP Java App on Kyma

[Official How-To deploy to Kyma](https://cap.cloud.sap/docs/guides/deployment/deploy-to-kyma#build-database-image)


- [provision a HANA Cloud instance in CF](https://developers.sap.com/tutorials/hana-cloud-deploying.html) 
- login to CF and the Kyma cluster
- create an HDI instance in CF, a service-key and a k8s secret for the HDI Container 
   ```bash
    ../scripts/create-db-secret.sh javasample-db capsample 
- Add the helm chart to the project 
  ```bash
    cds add helm
- configure the `your-container-registry` in `values.yaml` and the `domain`
- build database deployer image:**
    ```
    cds build --production
    pack build $YOUR_CONTAINER_REGISTRY/javasample-hana-deployer \
     --path db \
     --buildpack gcr.io/paketo-buildpacks/nodejs \
     --builder paketobuildpacks/builder:base
    ```
    (Replace `$YOUR_CONTAINER_REGISTRY` with the full-qualified hostname of your container registry)
- build image for CAP service:**
    ```
    mvn package
    pack build $YOUR_CONTAINER_REGISTRY/javasample-srv \
            --path srv/target/*-exec.jar \
            --buildpack gcr.io/paketo-buildpacks/sap-machine \
            --buildpack gcr.io/paketo-buildpacks/java \
            --builder paketobuildpacks/builder:base \
            --env SPRING_PROFILES_ACTIVE=cloud
    ```
- push images
    ```
    docker push $YOUR_CONTAINER_REGISTRY/javasample-hana-deployer
    
    docker push $YOUR_CONTAINER_REGISTRY/javasample-srv
    ```
- deploy to the Kyma cluster:
    ```
    helm upgrade bookshop ./chart --install -f values.yaml
    ```
  
## Initialize a CAP Java project

- initialize a new project```cds init <PROJECT-ROOT> --add java ```
- add sample CDS model ```mvn com.sap.cds:cds-maven-plugin:addSample```
- 