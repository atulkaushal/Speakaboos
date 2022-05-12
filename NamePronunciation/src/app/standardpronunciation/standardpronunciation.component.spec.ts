import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StandardpronunciationComponent } from './standardpronunciation.component';

describe('StandardpronunciationComponent', () => {
  let component: StandardpronunciationComponent;
  let fixture: ComponentFixture<StandardpronunciationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StandardpronunciationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StandardpronunciationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
