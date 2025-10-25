#!/bin/bash

TRIGGER_FILE="trigger.txt"

# Cria trigger.txt se não existir
if [ ! -f "$TRIGGER_FILE" ]; then
    echo "Criando trigger.txt..."
    echo $(date +%s) > "$TRIGGER_FILE"
fi

# Define profile dev e secret do DevTools
export SPRING_PROFILES_ACTIVE=dev
export SPRING_DEVTOOLS_REMOTE_SECRET=123

# Roda aplicação no modo dev (com .jar)
java -jar app.jar