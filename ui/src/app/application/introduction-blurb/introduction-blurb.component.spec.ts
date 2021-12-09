import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IntroductionBlurbComponent } from './introduction-blurb.component';

describe('IntroductionBlurbComponent', () => {
  let component: IntroductionBlurbComponent;
  let fixture: ComponentFixture<IntroductionBlurbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IntroductionBlurbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IntroductionBlurbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
