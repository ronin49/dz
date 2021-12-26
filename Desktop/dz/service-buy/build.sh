#!/usr/bin/env bash

docker build -t dmytronasyrov/service-cars:latest .
docker push dmytronasyrov/service-cars

minikube stop && minikube delete
minikube start --vm-driver hyperkit
kubectl apply -f workloads.yaml

echo "$(minikube ip)"
