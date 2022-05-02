import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'product',
        data: { pageTitle: 'gyanTatvApp.product.home.title' },
        loadChildren: () => import('./product/product.module').then(m => m.ProductModule),
      },
      {
        path: 'categories',
        data: { pageTitle: 'gyanTatvApp.categories.home.title' },
        loadChildren: () => import('./categories/categories.module').then(m => m.CategoriesModule),
      },
      {
        path: 'unit',
        data: { pageTitle: 'gyanTatvApp.unit.home.title' },
        loadChildren: () => import('./unit/unit.module').then(m => m.UnitModule),
      },
      {
        path: 'ware-house',
        data: { pageTitle: 'gyanTatvApp.wareHouse.home.title' },
        loadChildren: () => import('./ware-house/ware-house.module').then(m => m.WareHouseModule),
      },
      {
        path: 'product-inventory',
        data: { pageTitle: 'gyanTatvApp.productInventory.home.title' },
        loadChildren: () => import('./product-inventory/product-inventory.module').then(m => m.ProductInventoryModule),
      },
      {
        path: 'project',
        data: { pageTitle: 'gyanTatvApp.project.home.title' },
        loadChildren: () => import('./project/project.module').then(m => m.ProjectModule),
      },
      {
        path: 'consumption-details',
        data: { pageTitle: 'gyanTatvApp.consumptionDetails.home.title' },
        loadChildren: () => import('./consumption-details/consumption-details.module').then(m => m.ConsumptionDetailsModule),
      },
      {
        path: 'product-transaction',
        data: { pageTitle: 'gyanTatvApp.productTransaction.home.title' },
        loadChildren: () => import('./product-transaction/product-transaction.module').then(m => m.ProductTransactionModule),
      },
      {
        path: 'purchase-quotation',
        data: { pageTitle: 'gyanTatvApp.purchaseQuotation.home.title' },
        loadChildren: () => import('./purchase-quotation/purchase-quotation.module').then(m => m.PurchaseQuotationModule),
      },
      {
        path: 'purchase-quotation-details',
        data: { pageTitle: 'gyanTatvApp.purchaseQuotationDetails.home.title' },
        loadChildren: () =>
          import('./purchase-quotation-details/purchase-quotation-details.module').then(m => m.PurchaseQuotationDetailsModule),
      },
      {
        path: 'goods-recived',
        data: { pageTitle: 'gyanTatvApp.goodsRecived.home.title' },
        loadChildren: () => import('./goods-recived/goods-recived.module').then(m => m.GoodsRecivedModule),
      },
      {
        path: 'transfer',
        data: { pageTitle: 'gyanTatvApp.transfer.home.title' },
        loadChildren: () => import('./transfer/transfer.module').then(m => m.TransferModule),
      },
      {
        path: 'transfer-details',
        data: { pageTitle: 'gyanTatvApp.transferDetails.home.title' },
        loadChildren: () => import('./transfer-details/transfer-details.module').then(m => m.TransferDetailsModule),
      },
      {
        path: 'transfer-details-approvals',
        data: { pageTitle: 'gyanTatvApp.transferDetailsApprovals.home.title' },
        loadChildren: () =>
          import('./transfer-details-approvals/transfer-details-approvals.module').then(m => m.TransferDetailsApprovalsModule),
      },
      {
        path: 'transfer-recieved',
        data: { pageTitle: 'gyanTatvApp.transferRecieved.home.title' },
        loadChildren: () => import('./transfer-recieved/transfer-recieved.module').then(m => m.TransferRecievedModule),
      },
      {
        path: 'security-user',
        data: { pageTitle: 'gyanTatvApp.securityUser.home.title' },
        loadChildren: () => import('./security-user/security-user.module').then(m => m.SecurityUserModule),
      },
      {
        path: 'notification',
        data: { pageTitle: 'gyanTatvApp.notification.home.title' },
        loadChildren: () => import('./notification/notification.module').then(m => m.NotificationModule),
      },
      {
        path: 'client-details',
        data: { pageTitle: 'gyanTatvApp.clientDetails.home.title' },
        loadChildren: () => import('./client-details/client-details.module').then(m => m.ClientDetailsModule),
      },
      {
        path: 'product-client-agreement',
        data: { pageTitle: 'gyanTatvApp.productClientAgreement.home.title' },
        loadChildren: () => import('./product-client-agreement/product-client-agreement.module').then(m => m.ProductClientAgreementModule),
      },
      {
        path: 'parameter-lookup',
        data: { pageTitle: 'gyanTatvApp.parameterLookup.home.title' },
        loadChildren: () => import('./parameter-lookup/parameter-lookup.module').then(m => m.ParameterLookupModule),
      },
      {
        path: 'security-role',
        data: { pageTitle: 'gyanTatvApp.securityRole.home.title' },
        loadChildren: () => import('./security-role/security-role.module').then(m => m.SecurityRoleModule),
      },
      {
        path: 'security-permission',
        data: { pageTitle: 'gyanTatvApp.securityPermission.home.title' },
        loadChildren: () => import('./security-permission/security-permission.module').then(m => m.SecurityPermissionModule),
      },
      {
        path: 'user-access',
        data: { pageTitle: 'gyanTatvApp.userAccess.home.title' },
        loadChildren: () => import('./user-access/user-access.module').then(m => m.UserAccessModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
