import { ITransferDetails } from 'app/entities/transfer-details/transfer-details.model';
import { IProductClientAgreement } from 'app/entities/product-client-agreement/product-client-agreement.model';
import { ICategories } from 'app/entities/categories/categories.model';
import { IUnit } from 'app/entities/unit/unit.model';
import { ISecurityUser } from 'app/entities/security-user/security-user.model';
import { ProductType } from 'app/entities/enumerations/product-type.model';

export interface IProduct {
  id?: number;
  shortName?: string | null;
  hsnNo?: string | null;
  materialImageContentType?: string | null;
  materialImage?: string | null;
  isDeleted?: boolean | null;
  isActive?: boolean | null;
  productName?: string | null;
  alertUnits?: string | null;
  description?: string | null;
  basePrice?: number | null;
  quality?: string | null;
  qrCode?: string | null;
  barCode?: string | null;
  gstPercentage?: number | null;
  productType?: ProductType | null;
  lastModified?: string | null;
  lastModifiedBy?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  transferDetails?: ITransferDetails[] | null;
  productClientAgreements?: IProductClientAgreement[] | null;
  categories?: ICategories | null;
  unit?: IUnit | null;
  securityUser?: ISecurityUser | null;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public shortName?: string | null,
    public hsnNo?: string | null,
    public materialImageContentType?: string | null,
    public materialImage?: string | null,
    public isDeleted?: boolean | null,
    public isActive?: boolean | null,
    public productName?: string | null,
    public alertUnits?: string | null,
    public description?: string | null,
    public basePrice?: number | null,
    public quality?: string | null,
    public qrCode?: string | null,
    public barCode?: string | null,
    public gstPercentage?: number | null,
    public productType?: ProductType | null,
    public lastModified?: string | null,
    public lastModifiedBy?: string | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public freeField4?: string | null,
    public transferDetails?: ITransferDetails[] | null,
    public productClientAgreements?: IProductClientAgreement[] | null,
    public categories?: ICategories | null,
    public unit?: IUnit | null,
    public securityUser?: ISecurityUser | null
  ) {
    this.isDeleted = this.isDeleted ?? false;
    this.isActive = this.isActive ?? false;
  }
}

export function getProductIdentifier(product: IProduct): number | undefined {
  return product.id;
}
