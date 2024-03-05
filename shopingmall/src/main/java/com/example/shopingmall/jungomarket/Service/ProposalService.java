package com.example.shopingmall.jungomarket.Service;

import com.example.shopingmall.jungomarket.Dto.PurchaseProposalDto;
import com.example.shopingmall.jungomarket.Entity.ProductEntity;
import com.example.shopingmall.jungomarket.Entity.PurchaseProposal;
import com.example.shopingmall.jungomarket.repo.ProductRepository;
import com.example.shopingmall.jungomarket.repo.ProposalRepository;
import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import com.example.shopingmall.loginwithjwt.JWTutil.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProposalService {
    private final ProposalRepository proposalRepository;
    private final UserRepository userRepository;

    private final ProductRepository productRepository;
    public ProposalService(ProposalRepository proposalRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.proposalRepository = proposalRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void suggestItem(PurchaseProposalDto proposalDto){

        ProductEntity product =productRepository.findById(proposalDto.getProductId()).orElse(null);
        UserEntity proposer = userRepository.findById(proposalDto.getProposerId()).orElse(null);

        PurchaseProposal proposal = new PurchaseProposal();
        proposal.setProduct(product); // 제안된 물품 설정
        proposal.setProposer(proposer); // 제안자 설정
        proposal.setPrice(proposalDto.getPrice()); // 제안 가격 설정
        proposal.setStatus(proposalDto.getStatus()); // 제안 상태 설정

        proposalRepository.save(proposal);




    }

    public PurchaseProposal checkOne(Long id, PurchaseProposalDto proposalDto, String authentication) throws AccessDeniedException {
        ProductEntity product =productRepository.findById(proposalDto.getProductId()).orElse(null);
        if(!product.getOwner().getId().equals(authentication)){

            throw new AccessDeniedException("수정권한 x.");
        }

        UserEntity proposer = userRepository.findById(proposalDto.getProposerId()).orElse(null);


        PurchaseProposal purchaseProposal =proposalRepository.findByProcuctWhereUser(product,proposer).orElse(null);

        return purchaseProposal;


    }

    // Service 계층에서 사용될 메서드
    public List<PurchaseProposalDto> findMyProposals(Long productId, Long userId,String authentication) throws AccessDeniedException {

        ProductEntity product =productRepository.findById(productId).orElse(null);
        if(!product.getOwner().getId().equals(authentication)){

            throw new AccessDeniedException("수정권한 x.");
        }
        List<PurchaseProposal> proposals = proposalRepository.findByProductIdAndUserId(productId, userId);
        // 조회된 PurchaseProposal 엔터티 리스트를 PurchaseProposalDto 리스트로 변환
        return proposals.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // PurchaseProposal 엔터티를 PurchaseProposalDto로 변환하는 메서드
    private PurchaseProposalDto convertToDto(PurchaseProposal proposal) {
        PurchaseProposalDto dto = new PurchaseProposalDto();
        dto.setId(proposal.getId());
        dto.setProductId(proposal.getProduct().getId());
        dto.setProposerId(Math.toIntExact(proposal.getProposer().getId()));
        dto.setStatus(proposal.getStatus());
        dto.setPrice(proposal.getPrice());
        return dto;
    }

    public void accept(Long productId, Long proposalId,String authentication) throws AccessDeniedException {

        ProductEntity product =productRepository.findById(productId).orElse(null);
        if(!product.getOwner().getId().equals(authentication)){

            throw new AccessDeniedException("수정권한 x");
        }
        PurchaseProposal proposal =proposalRepository.findById(proposalId).orElse(null);
        proposal.setStatus("거래 승인 ");
        proposalRepository.save(proposal);

    }
    public void reject(Long productId, Long proposalId,String authentication) throws AccessDeniedException {

        ProductEntity product =productRepository.findById(productId).orElse(null);
        if(!product.getOwner().getId().equals(authentication)){

            throw new AccessDeniedException("수정권한 x");
        }
        PurchaseProposal proposal =proposalRepository.findById(proposalId).orElse(null);
        proposal.setStatus("거래 거절");
        proposalRepository.save(proposal);

    }

    public void cofirm(Long productId, Long proposalId,String authentication) throws AccessDeniedException {

        ProductEntity product =productRepository.findById(productId).orElse(null);
        if(!product.getOwner().getId().equals(authentication)){

            throw new AccessDeniedException("수정권한 x");


        }
        PurchaseProposal proposal =proposalRepository.findById(proposalId).orElse(null);
        proposal.setStatus("거래 확정");
        proposalRepository.save(proposal);

    }
}
