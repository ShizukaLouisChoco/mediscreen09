#!/bin/bash
docker-compose dowm
mvn clean install -DskipTests
docker-compose build --no-cache
docker-compose up -d --force-recreate