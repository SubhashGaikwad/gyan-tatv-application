import { PenaltyTerms } from 'app/entities/enumerations/penalty-terms.model';

export interface IProductClientAgreement {
  id?: number;
  price?: number | null;
  creditPeriod?: number | null;
  penalty?: number | null;
  penaltyTerms?: PenaltyTerms | null;
  notes?: string | null;
  image?: string | null;
  agreementPeriod?: number | null;
  termAndConditions?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  lastModified?: string | null;
  lastModifiedBy?: string | null;
}

export class ProductClientAgreement implements IProductClientAgreement {
  constructor(
    public id?: number,
    public price?: number | null,
    public creditPeriod?: number | null,
    public penalty?: number | null,
    public penaltyTerms?: PenaltyTerms | null,
    public notes?: string | null,
    public image?: string | null,
    public agreementPeriod?: number | null,
    public termAndConditions?: string | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public freeField4?: string | null,
    public lastModified?: string | null,
    public lastModifiedBy?: string | null
  ) {}
}

export function getProductClientAgreementIdentifier(productClientAgreement: IProductClientAgreement): number | undefined {
  return productClientAgreement.id;
}
