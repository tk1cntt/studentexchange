import React from 'react';
import { connect } from 'react-redux';

import OrderDetail from 'app/shared/layout/order/order-detail';

export interface IOrderDetailProp extends StateProps, DispatchProps {
  location: any;
  history: any;
}

export class Order extends React.Component<IOrderDetailProp> {
  render() {
    return <OrderDetail location={this.props.location} history={this.props.history} goto={'/order-detail'} />;
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  orderCartEntity: storeState.orderCart.entity,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = {};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Order);
