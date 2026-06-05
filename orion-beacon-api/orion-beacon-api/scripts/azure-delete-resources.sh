#!/bin/bash

# ============================================================
# Project Orion Beacon - Azure CLI
# Remove todos os recursos criados
# ============================================================

RESOURCE_GROUP="rg-orion-beacon-devops"

echo "Removendo Resource Group: $RESOURCE_GROUP"
echo "Todos os recursos associados serao excluidos."

az group delete \
  --name "$RESOURCE_GROUP" \
  --yes \
  --no-wait

echo "Solicitacao de remocao enviada."
