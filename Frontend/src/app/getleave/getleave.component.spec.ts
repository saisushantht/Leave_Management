import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetleaveComponent } from './getleave.component';

describe('GetleaveComponent', () => {
  let component: GetleaveComponent;
  let fixture: ComponentFixture<GetleaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetleaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetleaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
