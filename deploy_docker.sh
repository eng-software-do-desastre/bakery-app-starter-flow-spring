

## Build the application for control-center in production mode
mvn clean package -DskipTests -P production -P control-center

## You need to be logged in with your user, otherwise run `docker login`
USER=k8sdemos
docker build --tag $USER/bakery-cc:next .
docker push $USER/bakery-cc:next
