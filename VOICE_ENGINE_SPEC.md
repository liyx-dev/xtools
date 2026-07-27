# Voice Engine Specification
The Voice Engine is responsible for converting processed text into audio.

Responsibilities:
- Load available voices
- Change language
- Change speed
- Change pitch
- Generate audio
- Export audio
- Pause generation
- Resume generation
- Stop generation

The engine must never communicate directly with the UI.
The UI communicates only through the Voice Controller.
