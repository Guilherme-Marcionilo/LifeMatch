import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CadastroComponent } from './cadastro/cadastro.component';
import { DeletePostagemComponent } from './delete-postagem/delete-postagem.component';
import { DeleteTemaComponent } from './delete-tema/delete-tema.component';
import { FormPostagensComponent } from './form-postagens/form-postagens.component';
import { FormTemaComponent } from './form-tema/form-tema.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PainelComponent } from './painel/painel.component';
import { PutPostagemComponent } from './put-postagem/put-postagem.component';
import { PutTemaComponent } from './put-tema/put-tema.component';

const routes: Routes = [
  { path:'',redirectTo: 'home', pathMatch: 'full' },
  { path:'home',component: HomeComponent },
  { path:'login',component: LoginComponent },
  { path:'cadastro',component: CadastroComponent },
  { path:'painel',component: PainelComponent },
  { path:'form-postagens',component: FormPostagensComponent },
  { path:'form-tema',component: FormTemaComponent },
  { path:'editar-postagem/:id',component: PutPostagemComponent},
  { path:'deletar-postagem/:id',component: DeletePostagemComponent},
  { path:'editar-tema/:id',component: PutTemaComponent},
  { path:'deletar-tema/:id',component: DeleteTemaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
