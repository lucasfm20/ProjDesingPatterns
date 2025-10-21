## Documentação da Api.

## Gestão Médica

O propósito do projeto é, por meio de API’s, gerenciar um sistema de consultas, diagnosticos e tratamentos de vários pacientes com os seus respectivos médicos.

### Casos de Uso

1 - Cadastro e gerenciamento de pacientes \
2 - Agendamento e gerenciamento de consultas \
3 - Diagnostico de pacientes \
4 - Planejamento e gerenciamento de tratamentos \
5 - Gestão de médicos

## Ajuda:

Get: /ajuda


## Consulta:

Get: /consultas/id

Delete: /consultas/id

Post : /consultas 

```json
[
{
"pacienteId": Long,
"medicoId": Long,
"dataHora": LocalDateTime,
"descricao": String
}
]
```

## Diagnostico:

Get: /diagnosticos/id

Delete: /diagnosticos/id

Post : /diagnosticos

```json
[
{
"pacienteId": Long,
"medicoId": Long,
"dataHora": LocalDateTime,
"descricao": String
}
]
```

## Medico:

Get: /medicos/id

Delete: /medicos/id

Post : /medicos

```json
[
{
"nome": String,
"especialidade": String,
"contato": String
}
]
```

## Paciente:

Get: /pacientes/id

Delete: /pacientes/id

Patch: /pacientes/id

Post : /pacientes

```json
[
{
"nome": String,
"dataDeNascimento": LocalDateTime,
"contato": String
}
]
```

## Tratamento:

Get: /tratamentos/id

Delete: /tratamentos/id

Post : /tratamentos

```json
[
{
"diagnosticoId": Long,
"medicamentoId": String,
"descricao": String,
"duracao": String
}
]
```
