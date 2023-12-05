export interface Cidade {
  id: number;
  nome: string;
  ibgeId: number | null;
  siafiId: number | null;
}

export interface Estabelecimento {
  cidade: Cidade;
  situacao_cadastral: string; // Alterado para corresponder ao JSON
  data_cadastro: string | null; // Alterado para corresponder ao JSON
}

export interface DadosCnpjExterno {
  razao_social: string; // Alterado para corresponder ao JSON
  cidade: Cidade | null;
  situacao_cadastral: string; // Alterado para corresponder ao JSON
  data_cadastro: string | null; // Alterado para corresponder ao JSON
  estabelecimento: Estabelecimento;
  endereco?: string; // Mantido como opcional, preenchido pelo usuário
  telefone?: string; // Mantido como opcional, preenchido pelo usuário
}
