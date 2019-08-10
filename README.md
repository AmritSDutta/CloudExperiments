check-out WIKI for details : 
https://github.com/AmritSDutta/CloudExperiments/wiki 



CloudExperiments

Project : gkeemailing
description : Spring boot project for deployibg in GKE.

Project : emailing
description : Spring boot project for deployibg in GCP AppEngine.

Both project objective is to send mail using SendGrid.


REST endpoints : 
/getEmailReq  - responds with dummy JSON data with the format to be used for sending mail (GET)
/domail - method to be used for sending mail , format from earlier endpoint. (POST)
/sgevents - implementation of webhook, which can be used to consume notification from SendGrid. (POST)
    
