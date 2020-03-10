
package com.bibash.CanteenProject.api.Wallet.WalletRepo;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.bibash.CanteenProject.api.OrderItem.repository.OrderSpec;
import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.core.BaseSpecBuilder;

public class WalletSpecBuilder extends BaseSpecBuilder<Wallet> {

    public WalletSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<Wallet> getSpecification(String property, String filterValue) {
        return new WalletSpec(property , filterValue);
    }
}
