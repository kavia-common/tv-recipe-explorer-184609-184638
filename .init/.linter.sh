#!/bin/bash
cd /home/kavia/workspace/code-generation/tv-recipe-explorer-184609-184638/recipe_app_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

