import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { OrderModule } from 'ngx-order-pipe';
import { ModalModule } from 'ngx-bootstrap/modal';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './footer/footer.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { LoginComponent } from './login/login.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { PainelComponent } from './painel/painel.component';
import { FormPostagensComponent } from './form-postagens/form-postagens.component';
import { FormTemaComponent } from './form-tema/form-tema.component';
import { PutPostagemComponent } from './put-postagem/put-postagem.component';
import { PutTemaComponent } from './put-tema/put-tema.component';
import { DeletePostagemComponent } from './delete-postagem/delete-postagem.component';
import { DeleteTemaComponent } from './delete-tema/delete-tema.component';
import { AlertasComponent } from './alertas/alertas.component';
//import { FeedComponent } from './feed/feed.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    FooterComponent,
    CadastroComponent,
    LoginComponent,
    PainelComponent,
    FormPostagensComponent,
    FormTemaComponent,
    PutPostagemComponent,
    PutTemaComponent,
    DeletePostagemComponent,
    DeleteTemaComponent,
    AlertasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule,
    OrderModule,
    ModalModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
