# DocMind Backend

DocMind Backend is an AI-powered document management and question-answering backend built with **Java and Spring Boot**. It allows users to upload and manage documents, process their content, generate vector embeddings, and retrieve relevant information using a **Retrieval-Augmented Generation (RAG)** pipeline.

The application combines **OpenAI** with a **vector database** to provide context-aware responses based on the uploaded documents.

## 🚀 Key Features

* 📄 **Document Management**

  * Upload and manage documents
  * Store document metadata
  * Track document processing status
  * Extract and process document content

* 🤖 **AI-Powered Document Search**

  * Generate embeddings from document content
  * Store embeddings in a vector database
  * Perform semantic similarity searches
  * Retrieve relevant document chunks based on user queries

* 🔍 **RAG Pipeline**

  * User submits a question
  * Question is converted into an embedding
  * Relevant document chunks are retrieved from the vector database
  * Retrieved context is provided to OpenAI
  * OpenAI generates a context-aware response

* 🧠 **OpenAI Integration**

  * Uses OpenAI models for natural-language understanding and response generation
  * Uses embeddings to enable semantic document search

* 🗄️ **Vector Database**

  * Stores document embeddings
  * Enables similarity-based retrieval
  * Supports efficient semantic search over document content

## 🏗️ Architecture

```text
                    ┌─────────────────────┐
                    │       Client        │
                    │  REST API / UI      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Spring Boot API   │
                    │       Java          │
                    └──────────┬──────────┘
                               │
                 ┌─────────────┴─────────────┐
                 │                           │
                 ▼                           ▼
        ┌─────────────────┐        ┌─────────────────┐
        │ Document        │        │ Question /      │
        │ Processing      │        │ Query           │
        └────────┬────────┘        └────────┬────────┘
                 │                          │
                 ▼                          ▼
        ┌─────────────────┐        ┌─────────────────┐
        │ Text Chunking   │        │ Query Embedding │
        └────────┬────────┘        └────────┬────────┘
                 │                          │
                 ▼                          ▼
        ┌─────────────────────────────────────────────┐
        │              Vector Database                │
        │         PostgreSQL + pgvector               │
        └─────────────────────┬───────────────────────┘
                              │
                              │ Relevant Chunks
                              ▼
                    ┌─────────────────────┐
                    │    RAG Pipeline     │
                    │ Context Retrieval   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │       OpenAI        │
                    │  Response Generation│
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    AI Response      │
                    └─────────────────────┘
```

## 🔄 RAG Pipeline

DocMind uses a **Retrieval-Augmented Generation (RAG)** approach to answer questions using information from uploaded documents.

### 1. Document Upload

The user uploads a document through the backend API.

```text
Document → Backend → Document Processing
```

### 2. Text Extraction

The document content is extracted and prepared for processing.

### 3. Chunking

Large documents are divided into smaller chunks.

```text
Document
   ↓
Text
   ↓
Chunks
   ↓
Chunk 1
Chunk 2
Chunk 3
...
```

Chunking allows the system to retrieve only the relevant portions of a document instead of sending the entire document to the LLM.

### 4. Embedding Generation

Each document chunk is converted into a numerical vector using an embedding model.

```text
Text Chunk → OpenAI Embedding Model → Vector
```

### 5. Vector Storage

The generated vectors and associated document information are stored in the vector database.

The project uses **PostgreSQL with pgvector** for vector storage and similarity search.

### 6. User Query

When a user asks a question:

```text
User Question
      ↓
Query Embedding
      ↓
Vector Similarity Search
      ↓
Relevant Document Chunks
```

### 7. Context Augmentation

The retrieved chunks are added to the prompt as context.

```text
User Question
      +
Relevant Document Context
      ↓
OpenAI
```

### 8. Response Generation

OpenAI uses the retrieved context to generate a relevant answer.

```text
Retrieved Context + User Question
                ↓
              OpenAI
                ↓
        Context-Aware Answer
```

## 🛠️ Technology Stack

| Technology      | Purpose                               |
| --------------- | ------------------------------------- |
| Java            | Backend development                   |
| Spring Boot     | REST API and application framework    |
| Spring Data JPA | Database persistence                  |
| Hibernate       | ORM                                   |
| PostgreSQL      | Relational database                   |
| pgvector        | Vector storage and similarity search  |
| OpenAI          | Embeddings and AI response generation |
| RAG             | Retrieval-Augmented Generation        |
| Maven           | Dependency management and build       |
| Docker          | Containerization                      |

## 📂 Project Structure

```text
docmind-backend/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── docmind_backend/
│   │   │           ├── controller/
│   │   │           ├── service/
│   │   │           ├── repository/
│   │   │           ├── entity/
│   │   │           ├── config/
│   │   │           └── DocmindBackendApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
├── pom.xml
└── README.md
```

## ⚙️ Prerequisites

Make sure the following are installed:

* Java 21+
* Maven
* Docker
* PostgreSQL
* PostgreSQL with pgvector extension
* OpenAI API Key

## 🔐 Environment Configuration

Configure your environment variables before starting the application.

Example:

```bash
export OPENAI_API_KEY=your-openai-api-key
export DB_URL=jdbc:postgresql://localhost:5432/docmind
export DB_USERNAME=postgres
export DB_PASSWORD=your-password
```

For local development, you can also configure these values through your IDE's environment configuration.

> **Never commit API keys, passwords, or other secrets to GitHub.**

## ▶️ Running the Application

Clone the repository:

```bash
git clone https://github.com/Dheerendra007/docmind-backend.git
```

Navigate to the project:

```bash
cd docmind-backend
```

Build the project:

```bash
./mvnw clean install
```

Run the application:

```bash
./mvnw spring-boot:run
```

The backend will start on the configured application port.

## 🐳 Running with Docker

If Docker configuration is available in the project, the required services can be started using:

```bash
docker compose up -d
```

Check running containers:

```bash
docker ps
```

## 🔎 How Semantic Search Works

Traditional database search generally matches keywords.

For example:

```text
Query:
"What is the payment processing fee?"
```

A keyword-based search primarily looks for matching words.

With semantic search, the query is converted into a vector:

```text
"What is the payment processing fee?"
              ↓
        Embedding Model
              ↓
      [0.12, -0.43, 0.87, ...]
```

The vector database then finds document chunks with similar semantic meaning.

This allows DocMind to retrieve relevant information even when the exact words in the document are different from the user's question.

## 🎯 Use Cases

DocMind can be extended for:

* 📑 Document question answering
* 🏦 Financial and banking document analysis
* 📚 Knowledge-base systems
* 📋 Policy and compliance document search
* 🔎 Enterprise semantic search
* 🧾 Invoice and document analysis
* 📖 Research document analysis
* 💬 AI-powered document assistants

## 🔮 Future Enhancements

Potential future improvements include:

* [ ] Support for additional document formats
* [ ] Authentication and authorization
* [ ] Multi-user document management
* [ ] Conversation history
* [ ] Streaming AI responses
* [ ] Document-level access control
* [ ] Improved chunking strategies
* [ ] RAG evaluation and relevance scoring
* [ ] Hybrid keyword + vector search
* [ ] Re-ranking retrieved documents
* [ ] Chat history with persistent conversations
* [ ] Frontend application
* [ ] Production-ready observability and monitoring

## 🧠 What This Project Demonstrates

This project demonstrates practical implementation of:

* Java backend development
* Spring Boot REST APIs
* JPA/Hibernate
* PostgreSQL
* Vector databases
* pgvector
* OpenAI API integration
* Embeddings
* Semantic search
* Retrieval-Augmented Generation (RAG)
* Document processing
* AI-powered backend architecture
* Docker-based development

## 👨‍💻 Author

**Dheerendra Kapariya**

GitHub:
https://github.com/Dheerendra007

Repository:
https://github.com/Dheerendra007/docmind-backend

---

⭐ If you find this project useful, consider giving the repository a star.
