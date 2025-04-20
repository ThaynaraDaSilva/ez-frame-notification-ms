# 📬 Notification Service (`ez-frame-notification-ms`)

## 📌 Contextualização

O **Notification Service** é um microserviço da solução `ez-frame`, responsável por enviar notificações por **e-mail** aos usuários em caso de **falha no processamento de vídeos**.

- Ele expõe um **endpoint HTTP** que é chamado pelo `Ingestion Service` quando o status do vídeo é `"FAILED"`.
- Utiliza o **Amazon SES** para envio dos e-mails.

---

## 🧩 Desenho de Arquitetura

O diagrama abaixo representa o fluxo do `Notification Service` dentro da solução `ez-frame`, focando na **notificação de falhas**:

![image](https://github.com/user-attachments/assets/6550bc61-3e8b-4336-9b31-55b5dd910039)

> Para visualizar o diagrama, cole o script abaixo in PlantText.

```
@startuml
!define RECTANGLE class
!includeurl https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Component.puml

Container(ingestionService, "Ingestion Service", "Spring Boot (Java 21)", "Recebe status do Generator Service e envia falhas ao Notification Service")
Container(notificationService, "Notification Service", "Spring Boot (Java 21)", "Envia e-mails")
Container(ses, "SES", "Envia e-mails")

' Relacionamentos
ingestionService --> notificationService : "1. Chama endpoint (falhas, HTTP)"
notificationService --> ses : "2. Envia e-mail ao usuário (falha)"

' Estilização
skinparam monochrome true
skinparam shadowing false
skinparam backgroundColor #FFFFFF

@enduml
```

---

## ✅ Pré-requisitos

- ☕ Java 21 instalado
- 📦 Maven instalado
- 🔐 Credenciais AWS configuradas (`AWS CLI` ou arquivo `~/.aws/credentials`)
- 📧 Acesso ao Amazon SES com permissões adequadas
- 📨 Endereço de e-mail de origem verificado no SES (ex.: `seu-email@dominio.com`)

---

## 🗂️ Estrutura de Diretórios do Projeto

A estrutura segue o padrão **Clean Architecture**:

```
ez-frame-notification-ms/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/duosilva/tech/solutions/ez/frame/notification/ms/
│   │   │       ├── adapters/
│   │   │       │   ├── in/
│   │   │       │   │   └── controller/       # Controladores REST (ex.: NotificationController)
│   │   │       │   └── out/
│   │   │       │       └── email/           # Integração com SES (ex.: EmailAdapter)
│   │   │       ├── application/
│   │   │       │   ├── dto/                 # DTOs (ex.: NotificationRequest)
│   │   │       │   └── usecases/            # Casos de uso (ex.: SendNotificationUseCase)
│   │   │       ├── domain/
│   │   │       │   └── model/               # Modelos de domínio
│   │   │       └── config/                  # Configurações
│   │   └── resources/
│   │       └── application.yml              # Configurações do Spring Boot
├── pom.xml                                     # Arquivo Maven com dependências
└── README.md                                   # Documentação do projeto
```

---

## 🛠️ Como Compilar o Projeto

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/ThaynaraDaSilva/ez-frame-notification-ms.git
cd ez-frame-notification-ms
```

### 2️⃣ Configure o arquivo application.yml

```bash
aws:
  region: us-east-1
  ses:
    source-email: seu-email@dominio.com
server:
  port: 8080
```

### 3️⃣ Compile e execute o projeto

```bash
mvn clean install
mvn spring-boot:run
```

---

📡 Teste o Endpoint HTTP

```http
POST /send
```

📤 Exemplo de Payload

```json
{
  "videoId": "123",
  "status": "FAILED",
  "errorMessage": "Erro no processamento",
  "email": "seu-email@dominio.com"
}
```

🧪 Teste com cURL

```bash
curl -X POST http://localhost:8080/send \
  -H "Content-Type: application/json" \
  -d '{"videoId":"123","status":"FAILED","errorMessage":"Erro no processamento","email":"seu-email@dominio.com"}'
```

Ou utilize ferramentas como Postman.

---

## 🧱 Componentes da Solução Global ez-frame

| **Componente** | **Finalidade** | **Justificativa** |
| --- | --- | --- |
| **Clean Architecture** | Organização interna da solução | Foi escolhida para garantir uma estrutura modular, de fácil manutenção e testes. Essa separação clara entre regras de negócio e infraestrutura facilita a escalabilidade da solução ao longo do tempo, conforme o sistema evolui. |
| **Java 21** | Linguagem principal para implementação | A linguagem Java foi adotada em substituição ao .NET por uma decisão estratégica, considerando a expertise da equipe com o ecossistema Java. Essa escolha visa otimizar o desenvolvimento, reduzir a curva de aprendizado e garantir eficiência na evolução e manutenção da solução. |
| **Apache Maven** | Gerenciamento de dependências e build | Ferramenta amplamente utilizada no ecossistema Java, facilita a organização do projeto, o versionamento de dependências e o processo de build e deploy. |
| **Amazon EKS** | Orquestração dos microsserviços da solução | Solução gerenciada baseada em Kubernetes, que facilita o deploy, a escalabilidade e o gerenciamento dos microsserviços (`generator`, `ingestion`, `notification`), mantendo a consistência da infraestrutura. |
| **Amazon SES** | Envio de e-mails de notificação em caso de erro | Atende ao requisito de notificação automática para o usuário em caso de falha no processamento. É um serviço simples, eficiente e com baixo custo, ideal para esse tipo de comunicação. |
| **GitHub Actions** | Automatização de build, testes e deploys | O GitHub Actions foi escolhido por estar amplamente consolidado no mercado e por oferecer uma integração direta com repositórios GitHub, simplificando pipelines de entrega contínua. Além disso, a equipe já possui familiaridade com a ferramenta, o que reduz tempo de configuração e acelera o processo de entrega contínua. |

---

## 🔗 Demais Projetos Relacionados

**ez-frame-ingestion-ms** — Microserviço que envia vídeos para a fila de processamento, consulta status, e chama o `Notification Service` para enviar e-mails em caso de falha.

**ez-frame-generator-ms** — Microserviço que escuta a fila SQS para processar vídeos e retorna o status para o `Ingestion Service`.

---

## 👨‍💻 Desenvolvido por

@tchfer — RM357414

@ThaynaraDaSilva — RM357418
