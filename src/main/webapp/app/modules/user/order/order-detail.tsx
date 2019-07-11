import React from 'react';
import { connect } from 'react-redux';

import OrderDetail from 'app/shared/layout/order/order-detail';

export interface IOrderDetailProp extends StateProps, DispatchProps {
  location: any;
  history: any;
}

export class Order extends React.Component<IOrderDetailProp> {
  render() {
    return (
      <OrderDetail
        activeMenu="order-cart"
        activeSubMenu=""
        location={this.props.location}
        history={this.props.history}
        goto={'/order-cart'}
      />
    );
  }
}

const mapStateToProps = storeState => ({});

const mapDispatchToProps = {};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Order);
