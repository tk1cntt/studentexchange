import { Moment } from 'moment';

export const enum PaymentMethod {
  CASH = 'CASH',
  BANK_TRANSFER = 'BANK_TRANSFER',
  CARD = 'CARD',
  PAYPAL = 'PAYPAL'
}

export const enum PaymentType {
  DEPOSIT = 'DEPOSIT',
  ORDER_PAYMENT = 'ORDER_PAYMENT',
  REFUND = 'REFUND'
}

export const enum PaymentStatusType {
  PENDING = 'PENDING',
  PAID = 'PAID',
  CANCELED = 'CANCELED'
}

export interface IPayment {
  id?: number;
  amount?: number;
  code?: number;
  newBalance?: number;
  note?: string;
  orderCode?: string;
  method?: PaymentMethod;
  type?: PaymentType;
  status?: PaymentStatusType;
  createAt?: Moment;
  withdrawalFee?: number;
  staffApprovalLogin?: string;
  staffApprovalId?: number;
  staffCancelLogin?: string;
  staffCancelId?: number;
  customerLogin?: string;
  customerId?: number;
  createByLogin?: string;
  createById?: number;
}

export const defaultValue: Readonly<IPayment> = {};
