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
