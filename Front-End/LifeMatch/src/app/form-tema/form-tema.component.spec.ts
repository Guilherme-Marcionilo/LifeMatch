import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormTemaComponent } from './form-tema.component';

describe('FormTemaComponent', () => {
  let component: FormTemaComponent;
  let fixture: ComponentFixture<FormTemaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormTemaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormTemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
