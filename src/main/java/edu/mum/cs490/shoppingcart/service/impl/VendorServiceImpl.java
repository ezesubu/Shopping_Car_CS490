package edu.mum.cs490.shoppingcart.service.impl;

import edu.mum.cs490.shoppingcart.domain.*;
//import edu.mum.cs490.shoppingcart.service.IMailService;
import edu.mum.cs490.shoppingcart.springboot.observer.*;
import edu.mum.cs490.shoppingcart.springboot.template.TransactionTemplate;
import edu.mum.cs490.shoppingcart.springboot.template.impl.VendorRegistrationTemplateImpl;
import edu.mum.cs490.shoppingcart.repository.CardDetailRepository;
import edu.mum.cs490.shoppingcart.repository.VendorRepository;
import edu.mum.cs490.shoppingcart.service.IPaymentService;
import edu.mum.cs490.shoppingcart.service.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Service
public class VendorServiceImpl extends UserServiceImpl<Vendor> implements IVendorService {

    protected final VendorRepository repository;
    private final CardDetailRepository cardDetailRepository;
    private final IPaymentService paymentService;
//    private final IMailService mailService;

    @Value("${card.detail.id.oss}")
    private Integer cardDetailIdOSS;
    @Value("${vendor.registration.fee}")
    private Double vendorRegistrationFee;



    @Autowired
    public VendorServiceImpl(VendorRepository repository, CardDetailRepository cardDetailRepository, IPaymentService paymentService /*IMailService mailService*/) {
        super(repository);
        this.repository = repository;
        this.cardDetailRepository = cardDetailRepository;
        this.paymentService = paymentService;
//        this.mailService = mailService;
    }

    @Override
    public List<Vendor> find(String username, String companyName, Status status) {
        return repository.find(username, companyName, status);
    }

    @Override
    public Page<Vendor> findPage(String username, String companyName, Status status, Pageable pageable) {
        return this.repository.findPage(username, companyName, status, pageable);
    }

    @Override
    public Vendor getByCompanyName(String companyName) {
        return null;
    }

    @Override
    public Integer transferFee(CardDetail vendorCardDetail, Vendor vendor) {
        CardDetail OSSCardDetail = cardDetailRepository.findById(cardDetailIdOSS).get();
        TransactionTemplate transactionTemplate = getVendorRegistrationTemplate(vendorCardDetail, OSSCardDetail, vendor);
        return transactionTemplate.process();
    }



    private TransactionTemplate getVendorRegistrationTemplate(CardDetail vendorCardDetail, CardDetail OSSCardDetail, Vendor vendor) {
        NotifierSubject notifierSubject = new NotifierSubject();
        //notifierSubject.addObserver(new MailToVendorAndAdminObserver(vendor, mailService));
        TransactionTemplate purchaseTemplate = new VendorRegistrationTemplateImpl(vendorCardDetail, OSSCardDetail, notifierSubject, new TransferSubject(), paymentService, vendorRegistrationFee);
        return purchaseTemplate;
    }
}
