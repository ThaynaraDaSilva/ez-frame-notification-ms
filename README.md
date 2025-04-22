# 📬 ez-frame-notification-ms

## 📌 Contextualização

O microserviço `ez-frame-notification-ms` é responsável por notificar usuários sobre falhas no processamento de vídeos. Suas principais funções incluem:

- **Envio de notificações**: Processa chamadas do `ez-video-ingestion-ms` no endpoint `/send` quando o status do vídeo é `FAILED`.
- **Envio de e-mails**: Utiliza o AWS SES para enviar notificações por e-mail aos usuários, informando sobre o problema ocorrido.

---

## 🧩 Fluxo de Interação entre Serviços

O diagrama abaixo ilustra o fluxo do `ez-frame-notification-ms` (em vermelho) e suas interações com outros componentes do sistema.

![image](https://github.com/user-attachments/assets/afeb5381-5d47-4cd2-a5a0-c87ec59c1a1d)

---

## ✅ Pré-requisitos para solução ez-frame (Todos os Microserviços)

- ☕ Java 21 instalado
- 📦 Maven instalado
- 🔐 Credenciais AWS configuradas no repositório como SECRETS
- 🔐 Credenciais do SonarQube configuradas no repositório como SECRETS
- 📧 Criar entity (endereço de e-mail de origem verificado no SES) no Amazon SES e usuário e política no IAM (sendEmail, sendRawEmail)
- Criar userpool e appClient no Cognito

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
