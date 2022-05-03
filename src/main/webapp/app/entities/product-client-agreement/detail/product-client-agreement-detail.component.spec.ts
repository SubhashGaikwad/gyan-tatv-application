import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProductClientAgreementDetailComponent } from './product-client-agreement-detail.component';

describe('ProductClientAgreement Management Detail Component', () => {
  let comp: ProductClientAgreementDetailComponent;
  let fixture: ComponentFixture<ProductClientAgreementDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProductClientAgreementDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ productClientAgreement: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ProductClientAgreementDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ProductClientAgreementDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load productClientAgreement on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.productClientAgreement).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
