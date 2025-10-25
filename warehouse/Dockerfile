# Container de build
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build

ENV INSTALL_PATH=/warehouse

WORKDIR $INSTALL_PATH

# Atualiza pacotes para corrigir vulnerabilidades de sistema
RUN apt-get update && apt-get upgrade -y && apt-get install -y --no-install-recommends ca-certificates && rm -rf /var/lib/apt/lists/*

# Copia arquivos do projeto
COPY pom.xml .
COPY src ./src

# Gera o JAR ignorando testes
RUN mvn clean package -DskipTests

# copia script de inicialização para ambiente de desenvolvimento
COPY start-dev.sh .

# copia arquivo trigger para forçar rebuild no ambiente de desenvolvimento
# Container final para rodar o JAR
FROM eclipse-temurin:21-jre-jammy

# Atualiza pacotes no container final para corrigir vulnerabilidades
RUN apt-get update && apt-get upgrade -y && apt-get install -y --no-install-recommends ca-certificates && rm -rf /var/lib/apt/lists/*

WORKDIR /warehouse
FROM eclipse-temurin:21-jre-jammy

WORKDIR /warehouse

# Copia JAR gerado
COPY --from=build /warehouse/target/warehouse-0.0.1-SNAPSHOT.jar app.jar


# Exposição da porta 8081
EXPOSE 8081

# Roda a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]