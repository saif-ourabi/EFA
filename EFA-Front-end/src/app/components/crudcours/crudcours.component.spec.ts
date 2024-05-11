import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudcoursComponent } from './crudcours.component';

describe('CrudcoursComponent', () => {
  let component: CrudcoursComponent;
  let fixture: ComponentFixture<CrudcoursComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CrudcoursComponent]
    });
    fixture = TestBed.createComponent(CrudcoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
