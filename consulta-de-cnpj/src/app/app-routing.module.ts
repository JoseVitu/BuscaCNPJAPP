import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmpresaComponent } from './empresa/empresa.component';


const routes: Routes = [
{ path: '', redirectTo: '/empresa', pathMatch: 'full' },
{ path: 'empresa', component: EmpresaComponent } ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
