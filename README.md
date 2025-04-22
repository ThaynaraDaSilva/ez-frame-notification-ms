# 📬 ez-frame-notification-ms

## 📌 Contextualização

O microserviço `ez-frame-notification-ms` é responsável por notificar usuários sobre falhas no processamento de vídeos. Suas principais funções incluem:

- **Recebimento de notificações**: Processa chamadas do `ez-video-ingestion-ms` no endpoint `/send` quando o status do vídeo é `FAILED`.
- **Envio de e-mails**: Utiliza o AWS SES para enviar notificações por e-mail aos usuários, informando sobre o problema ocorrido.

---

## 🧩 Desenho de Fluxo

O diagrama abaixo ilustra o fluxo do `ez-frame-notification-ms` (em vermelho) e suas interações com outros componentes do sistema.

![image](https://github.com/user-attachments/assets/7b1bd033-b96d-4704-81c0-9ab3635d22df)

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
│   │   │       │   │   └── controller/       # Controladores REST
│   │   │       │   └── out/
│   │   │       │       └── email/           # Integração com SES
│   │   │       ├── application/
│   │   │       │   ├── dto/                 # DTOs
│   │   │       │   └── usecases/            # Casos de uso
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

**ez-frame-ingestion-ms** — Microserviço que serve como porta de entrada para o upload e gerenciamento de vídeos.

**ez-frame-generator-ms** — Microserviço que é responsável pelo processamento assíncrono de vídeos, gerando frames e arquivos compactados.

---

## 👨‍💻 Desenvolvido por

@tchfer — RM357414

@ThaynaraDaSilva — RM357418
