# GraphQL Federation — Fase 1

Proyecto end-to-end con Apollo Federation v2, DGS (Java), y Apollo Router.

## Subgraphs
- **Products** → puerto `8080`
- **Reviews** → puerto `8081`

## Router
Apollo Router desplegado en k3d via Helm → puerto `4000`

## Requisitos
- Java 21
- Maven 3.9+
- OrbStack + k3d
- Rover CLI
- Helm 4.x
