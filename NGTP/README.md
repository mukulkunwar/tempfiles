# Decoder Middleware – Documentation

## 📑 Table of Contents
1. [Overview](#1-overview)
2. [Use Case](#2-use-case)
3. [YAML Configuration Reference](#3-yaml-configuration-reference)
4. [Kafka Message Format](#4-kafka-message-format)
5. [Decoded Model Class Specifications](#5-decoded-model-class-specifications)

---

## 1. 🧩 Overview

This Spring Boot–based middleware sits between an upstream data gateway (F5) and Huawei's Commodore decoding service. It intercepts incoming HTTP requests containing binary payloads, optionally decodes them based on pre-configured rules, forwards them to Huawei, and logs both forwarding and decoding results to Kafka for monitoring and auditing.

---

## 2. 🚗 Use Case

The middleware is primarily used for:

- Decoding vehicle telematics data (e.g., for device type `ATB4S`)
- Testing and validating decoding logic independently
- Comparing decoded results against Huawei output
- Producing structured telemetry logs into Kafka for downstream processing
- Acting as a proxy for forwarding payloads during transition/migration phases

---

## 3. ⚙️ YAML Configuration Reference

| YAML Property                          | Type     | Description |
|----------------------------------------|----------|-------------|
| `proxy.enable-global-forwarding`       | boolean  | Enables or disables HTTP forwarding globally |
| `proxy.enable-global-decoding`         | boolean  | Enables or disables decoding functionality |
| `proxy.forward-first`                  | boolean  | If true: forward first, then decode. If false: decode first |
| `proxy.target-base-url`                | string   | Base URL of the downstream server (e.g., mock Huawei) |
| `proxy.kafka-enabled`                  | boolean  | Enables Kafka logging of decode and forward results |
| `proxy.kafka-topic-success`            | string   | Kafka topic name for successful or decoded logs |
| `proxy.kafka-topic-failure`            | string   | Kafka topic name for logs when errors occur |
| `proxy.path-mapping`                   | map      | Maps incoming URI paths to downstream endpoints |
| `proxy.headers-to-remove`              | list     | List of header names to exclude before forwarding |
| `proxy.headers-to-add`                 | map      | Headers to inject into forwarded request |
| `proxy.forwarding-rules`               | list     | List of rules for enabling decoding and forwarding per `deviceType` + `messageType` |
| `proxy.forwarding-rules[].device-type` | enum     | Enum string for device type (e.g., `ATB4S`) |
| `proxy.forwarding-rules[].message-types[].id`                  | integer  | Message type identifier (e.g., `13`, `16`, `103`) |
| `proxy.forwarding-rules[].message-types[].decode`             | boolean  | Whether to decode this message type |
| `proxy.forwarding-rules[].message-types[].forward-on-decode-success` | boolean | Whether to forward if decoding succeeds |
| `proxy.forwarding-rules[].message-types[].forward-on-decode-failure` | boolean | Whether to forward if decoding fails |

---

## 4. 🔁 Kafka Message Format

### Top-Level Fields

| Field             | Type     | Description |
|------------------|----------|-------------|
| `uuid`           | string   | Unique trace ID for the request |
| `timestamp`      | string   | ISO-8601 timestamp when request was processed |
| `deviceType`     | string   | Device type of the message (e.g., `ATB4S`) |
| `messageType`    | string   | Message type code (e.g., `13`, `103`) |
| `httpPath`       | string   | HTTP path used in the incoming request |
| `rawPayload`     | string   | Original payload in hex string format |
| `decodeStatus`   | object   | Status of decoding operation (see breakdown below) |
| `forwardStatus`  | object   | Status of HTTP forwarding (see breakdown below) |
| `decodedMessage` | object   | JSON structure of the decoded message (optional) |
| `error`          | object   | Error information (only present on failure) |

### decodeStatus Object

| Field    | Type     | Description |
|----------|----------|-------------|
| `status` | string   | One of `SUCCESS`, `FAILURE`, `SKIPPED` |
| `reason` | string   | Optional explanation (e.g., `DECODE_EXCEPTION`, `RULE_BLOCKED`) |

### forwardStatus Object

| Field         | Type     | Description |
|---------------|----------|-------------|
| `status`      | string   | One of `SUCCESS`, `FAILURE`, `SKIPPED` |
| `httpStatus`  | integer  | HTTP status returned by forwarded service |
| `responseBody`| string   | Raw response body from forwarded service |

### error Object

| Field        | Type     | Description |
|--------------|----------|-------------|
| `message`    | string   | Short explanation of the failure |
| `stackTrace` | string   | Full or trimmed stack trace |

---

## 5. 🧬 Decoded Model Class Specifications

(Coming soon)

To be populated with field-level specifications for:
- `Header`
- `DetailedInfoP1`, `DetailedInfoP2`, `DetailedInfoP3`
- `PrivacyModeInfo`
- `StatusInfo`