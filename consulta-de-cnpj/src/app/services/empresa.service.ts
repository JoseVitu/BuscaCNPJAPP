import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {
  private apiUrl = 'http://localhost:8080/empresa';

  constructor(private http: HttpClient) {}

  buscarEmpresaPorCnpj(cnpj: string): Observable<any> {
    const url = `${this.apiUrl}/${cnpj}`;
    console.log(`Fazendo requisição para: ${url}`);
    return this.http.get(url);
  }

  salvarEmpresa(empresa: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, empresa);
  }
}
