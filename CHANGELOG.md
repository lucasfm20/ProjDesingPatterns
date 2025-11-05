# Changelog

Todas as mudanças relevantes deste projeto serão documentadas aqui.

O formato segue as práticas de [Keep a Changelog](https://keepachangelog.com/pt-BR/1.1.0/) e a numeração de versões adota [SemVer](https://semver.org/lang/pt-BR/).

## [0.1.0] - 2025-11-05

### Adicionado

- Estrutura inicial do domínio de gestão médica com modelos para `Paciente`, `Medico`, `Consulta`, `Diagnostico` e `Tratamento`.
- Controladores REST para cadastro, consulta e exclusão das principais entidades em `controllers`.
- Serviços que encapsulam as regras de negócio centrais e interação com os repositórios JPA.
- Mapeadores dedicados para converter entidades em DTOs e vice-versa, reduzindo duplicidade nas camadas de serviço.
- Adaptador `ConsultaAdapter` para integração com consultas externas, incluindo DTOs e controlador específico.
- Estratégias de validação (`validator`) para conflitos de agenda e consistência de dados antes do agendamento.


