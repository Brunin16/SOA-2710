#!/bin/bash

cd "$(dirname "$BASH_SOURCE")" || exit 1


if docker network inspect "brunin" >/dev/null 2>&1; then
  echo "network existente"
else
  docker network create brunin
fi

echo "Iniciando Mysql"
#docker-compose -f docker-compose-infra.yml up -d

echo "Aguardando inicialização do mysql"
sleep 5

echo "Subindo Aplicacao"
docker-compose -f docker-compose.yml up -d