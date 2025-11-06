# Changelog

Todas as mudanças relevantes deste projeto serão documentadas aqui.

O formato segue as práticas de [Keep a Changelog](https://keepachangelog.com/pt-BR/1.1.0/) e a numeração de versões adota [SemVer](https://semver.org/lang/pt-BR/).

## [2.0.0] - 2025-11-05

### Adicionado

- Camada de mapeadores (`ConsultaMapper`, `DiagnosticoMapper`, `MedicoMapper`, `PacienteMapper`, `TratamentoMapper`) para centralizar conversões entre entidades e DTOs.
- `GlobalExceptionHandler` com tratamento consolidado de erros e nova `ConsultaNotFoundException`.
- Bateria de testes unitários cobrindo controllers, services e validadores, garantindo confiabilidade nas principais regras de negócio.
- Registro formal do histórico de versões por meio deste `CHANGELOG.md`.

### Alterado

- Controllers atualizados para consumir os mapeadores, reduzir duplicidade e padronizar respostas HTTP.
- Serviços refatorados com remoção de validações repetidas, nomes mais claros e melhorias apontadas por code smells.
- `pom.xml` ajustado com descrição alinhada ao domínio da aplicação e versionamento interno atualizado.
- `README.md` expandido com análise de problemas, estratégia de refatoração e instruções para manutenção contínua.

### Corrigido

- Tratamento consistente de exceções relacionadas a consultas, evitando respostas genéricas.
- Ajustes pontuais de estilo e formatação em classes principais e arquivos de configuração.

## [1.0.0] - 2025-10-21

### Adicionado

- Estrutura inicial do domínio de gestão médica com entidades e DTOs para `Paciente`, `Medico`, `Consulta`, `Diagnostico` e `Tratamento`.
- API REST com endpoints CRUD completos nas controllers correspondentes.
- Serviços encapsulando regras de negócio, incluindo validação de conflitos de agenda e consistência de dados antes do agendamento.
- Adaptador `ConsultaExternaAdapter` para integração com consultas externas, incluindo DTOs e controlador específico.
- Estratégias de validação (`ValidaConflitoHorario`, `ValidaPaciente`, `ValidadorConsulta`) para proteger regras críticas do sistema.
