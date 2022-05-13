import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { StandardpronunciationComponent } from './standardpronunciation/standardpronunciation.component';
import {MatButtonModule} from '@angular/material/button';
import {FormGroupDirective,FormsModule} from '@angular/forms'

@NgModule({
  declarations: [
    AppComponent,
    StandardpronunciationComponent
  ],
  imports: [
    BrowserModule,
    MatButtonModule,
    FormsModule

  ],
  providers: [FormGroupDirective],
  bootstrap: [AppComponent]
})
export class AppModule { }
