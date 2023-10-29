## docker ps --format "table {{.ID}}\t{{.Status}}\t{{.Names}}"
## docker network ls
## docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' 8e3fa1c64497
## kafka-console-consumer --bootstrap-server 172.21.0.3:9092 --topic batman --from-beginning
## docker exec -it CONTAINER_ID /bin/bash
