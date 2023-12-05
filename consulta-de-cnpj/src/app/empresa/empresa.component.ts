import { Component } from '@angular/core';
import { EmpresaService } from '../services/empresa.service';
import {DadosCnpjExterno} from "../models/empresa.model";


@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent {
  cnpj: string = '';
  empresa: DadosCnpjExterno | null = null;
  erro: string = '';
  cnpjNumerico: string = '';
  constructor(private empresaService: EmpresaService) {}


  buscarEmpresa() {
    this.empresaService.buscarEmpresaPorCnpj(this.cnpjNumerico)
      .subscribe(
        data => {
          // ajustando os dados para corresponderem à api em springboot.
          this.empresa = {
            razao_social: data.razao_social,
            cidade: data.cidade,
            situacao_cadastral: data.situacao_cadastral,
            data_cadastro: data.data_cadastro,
            estabelecimento: {
              ...data.estabelecimento,
              situacao_cadastral: data.estabelecimento.situacao_cadastral,
              data_cadastro: data.estabelecimento.data_cadastro,
              cidade: {
                ...data.estabelecimento.cidade,
                id: data.estabelecimento.cidade.id,
                nome: data.estabelecimento.cidade.nome,
              }
            },
            // inicializando dados vazios, será preenchido pelo usuario
            endereco: '',
            telefone: ''
          };
          this.erro = '';
        },
        error => {
          this.erro = 'Erro ao buscar empresa.';
          console.error(error);
        }
      );
  }

  salvarEmpresa() {
    //dados que serão salvos pela API (temos dados vindo da API e dados definidos pelo usuário)
    const empresaInputModel = {
      cnpj: this.cnpjNumerico,
      endereco: this.empresa?.endereco,
      telefone: this.empresa?.telefone,
      dadosCnpjExterno: this.empresa,
      data_cadastro: this.empresa?.data_cadastro
    };

    this.empresaService.salvarEmpresa(empresaInputModel)
      .subscribe(
        data => {
          alert('Empresa salva com sucesso!');
        },
        error => {
          this.erro = error.error;
          console.error('Erro ao salvar empresa:', error);
        }
      );
  }


  formatarCnpj(valor: string): void {
    this.cnpjNumerico = valor.replace(/\D/g, '');

   //limitação 14 digitos
    if (this.cnpjNumerico.length > 14) {
      this.cnpjNumerico = this.cnpjNumerico.substring(0, 14);
    }

    //mascara de formatação
    let valorFormatado = this.cnpjNumerico;
    valorFormatado = valorFormatado.replace(/^(\d{2})(\d)/, '$1.$2');
    valorFormatado = valorFormatado.replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3');
    valorFormatado = valorFormatado.replace(/\.(\d{3})(\d)/, '.$1/$2');
    valorFormatado = valorFormatado.replace(/(\d{4})(\d)/, '$1-$2');

    this.cnpj = valorFormatado;
  }


  formatarTelefone(valor: string): void {
    // remove carateres nao numericos
    valor = valor.replace(/\D/g, '');
    if (valor.length > 11) {
      //limita 11 digitos
      valor = valor.substring(0, 11);
    }
    //formata telefone
    valor = valor.replace(/^(\d{2})(\d)/g, '($1) $2');
    valor = valor.replace(/(\d{5})(\d)/, '$1-$2');
    this.empresa!.telefone = valor;
  }

}
