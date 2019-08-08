# CloudExperiments
Project : gkeemailing

Create blank Spring boot project , with web as dependency.

Modify the blank project as required. 3.Expose at least one endpoint for testing (REST service.)

create docker file as in the repo.

Build your container image using Cloud Build to build and docker push, but it happens on GCP. Replace PROJECT_ID
gcloud builds submit --tag gcr.io/[ProjectID]/gkeemailing

Create the cluster. Replace YOUR_GCP_ZONE sudo gcloud container clusters create gkeemailing 
--num-nodes 1 
--enable-basic-auth 
--issue-client-certificate 
--zone asia-south1 
--no-enable-autoupgrade

verify kubectl get nodes

Create the deployment.yaml (as in the proj), deploy and verify. sudo kubectl apply -f deployment.yaml sudo kubectl get deployments sudo kubectl get pods

9.Create the file service.yaml, apply and verify. sudo kubectl apply -f service.yaml sudo kubectl get services

10. findout the External IP , and access it. for test- curl [EXTERNAL_IP]
