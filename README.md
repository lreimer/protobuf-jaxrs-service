# Protocol Buffers API with JAX-RS on Java EE 8

A REST API is often not a good fit if what you really need is an RPC interface.
But you can use Google Protocol Buffers as an efficient alternative and piggyback
the binary payload using a simple JAX-RS endpoint in Java EE 8.

## Usage

To run everything locally, you need to first compile and build the showcase image.
```
$ ./gradlew assemble
$ docker-compose up --build
```

## References

- https://developers.google.com/protocol-buffers/docs/overview
- https://developers.google.com/protocol-buffers/docs/javatutorial
- https://github.com/google/protobuf-gradle-plugin

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE`
file for details.
