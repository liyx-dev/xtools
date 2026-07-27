# Xtools Architecture
## Core

The Core contains reusable components shared by every module.

- Text Processor
- Chunk Engine
- Queue Engine
- File Manager
- Settings Manager
- Logger

---

## Modules
Every feature is a module.

Current modules:

- Voice Studio

Future modules:

- AI Writer
- PDF Reader
- OCR Scanner
- Subtitle Creator
- Image Tools

---

## Voice Studio Pipeline
Raw Text

↓

Text Processor

↓

Chunk Engine

↓

Queue Engine

↓

Voice Engine

↓

Audio Export

↓

History

---

The UI never communicates directly with the Voice Engine.
The UI only communicates with controllers.
