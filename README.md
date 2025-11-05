# Documentação da Api.

## Gestão Médica

O propósito do projeto é, por meio de API’s, gerenciar um sistema de consultas, diagnosticos e tratamentos de vários pacientes com os seus respectivos médicos.

## Histórico de alterações

Consulte o arquivo [`CHANGELOG.md`](CHANGELOG.md) para acompanhar as versões e registrar qualquer nova mudança relevante antes de concluir o merge de uma feature.

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

## Análise dos Problemas Detectados 

- Uso de try catch com exceções genéricas. 

- O código de conversão convertToDTO() e convertToEntity() é repetitivo e polui a classe de serviço. 

- Alguns métodos não são tão claros como o findAll() da classe ConsultaServices. 

- Poucos comentários no código, dificultando a compreensão do mesmo. 



## Estratégia de Refatoração 

- Criar exceções específicas.
  
- Criar uma classe mapper para deixar o código mais limpo e manter as classes services apenas com a regra de negócio.
  
- Deixar as variáveis e métodos mais claros de suas funcionalidades.
  
- Adicionar comentários das principais funções do código, facilitando uma futura manutenção.
  
- Utilização de linter Checkstyle para manter a padronização do código. 
