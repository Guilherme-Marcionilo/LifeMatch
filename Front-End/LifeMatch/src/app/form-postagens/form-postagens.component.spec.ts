import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPostagensComponent } from './form-postagens.component';

describe('FormPostagensComponent', () => {
  let component: FormPostagensComponent;
  let fixture: ComponentFixture<FormPostagensComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormPostagensComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormPostagensComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
