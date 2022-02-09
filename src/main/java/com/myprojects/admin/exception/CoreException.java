/**
 * Parent class for all Exceptions
 *
 * @author suganda
 */
package com.myprojects.admin.exception;

import org.apache.log4j.Logger;

public class CoreException extends Exception {

    private static final Logger errorLogger = Logger.getLogger("ERRORS");
    private static final long serialVersionUID = 2424676613550241791L;

    public enum ExceptionType {
        /** Generic Exceptions **/
        resultSizeTooLarge,

        organizationUsedByUser, organizationWithMacAddresses,

        /** Price Class & Category Exceptions **/
        priceClassMissing,
        priceClassDiscountConfiguredInfiniteLoop,
        priceClassUsedInPending,
        priceClassUsedInLive,
        priceClassUsedInOtherModules,
        priceClassUsedInPriceModelTemplate,
        priceClassUsedAsDiscount,
        priceClassIsLastInPriceModelTemplate,
        priceClassIsStandardInPriceModelTemplate,
        priceCategoryMissing,
        priceCategoryUsedInPending,
        priceCategoryUsedInLive,
        priceCategoryUsedInPriceModelTemplate,
        priceCategoryUsedAsDiscount,
        priceCategoryIsLastInPriceModelTemplate,
        /** End of Price Class & Category Exceptions **/

        /** Sales Exceptions **/
        priceClassInsufficientQuota,
        productOffSales,
        promotionPasswordNotValid,
        inventoryNoLongerReserved,
        inventoryReservationError,
        inventoryCommitError,
        creditCardBasicValidationFailed,
        creditCardRangeNotValid,
        creditCardRangeInsufficientAmount,
        creditCardDeliveryMethodNoSplitPayment,
        creditCardDeliveryMethodRequired,
        invalidAmountPaid,

        creditCardDiscountNoVouchers,
        creditCardDiscountNoVouchersInternet,
        /** End of Sales Exceptions **/

        ticketTemplateNotFound,        //PDPA - added by Than
        // NG Mobile Cart - chiavoon
        tticketTemplateNotFound,        //PDPA - added by chiavoon

        /** Batch Print Join Exceptions **/
        printStatusUpdateNotRequired,
        deliveryStatusUpdateNotRequired,

        /** Internet content Exceptions*/
        internetContentStatusDraft,
        internetContentPromoterUnattached,
        internetContentDateTimeRangeException,
        /** End of Batch Print Join Exceptions **/

        /** [Start] Membership Tier - Membership Sales Via Box Office - added by webster **/
        /** Multi-Membership Tier validation **/
        multiMembership,
        membershipNoMatch,
        excessMembershipProductGroupQuota,
        membershipConfigMandatoryFail,    //Membership Tier - config enhancement  - added by webster
        //[Start] NG Membership - Allow purchase of Joint & Family membership - webster
        supplementaryMemberAlreadyRegisterMembership, 	
        duplicateSupplementaryMember, 	
        submemberInvalidDate,
        membershipRulesValidationFail,
        //[End] NG Membership - Allow purchase of Joint & Family membership - webster
        /** [End] Membership Tier - Membership Sales Via Box Office - added by webster **/

        //sh srt
        membershipPointsNotMatch, // sh srt
		membershipPointsInsufficient, // sh srt
		membershipPointsExpired, //sh srt
		membershipPointsUtilised, //sh srt
		membershipAwardedPointsExpired, //sh srt
		
        /** User creation Exceptions **/
        duplicateUserError,
        userCreationError,
        /** duplicated seats **/
        duplicatedSeats,
        /** End of User creation Exceptions **/

        //added by Devin for EVoucher Enhancement
        evoucherError,
        
        // Added for subscription validation
        promoterDirectMailLocalFailNoAddress,
        promoterDirectMailLocalFailInvalidCountry,


        // [Start] CR#2933, #2917  (Quota and Ticket Quantity Restriction)

        sharedQuotaExceed,
        ticketRestrictionTransaction,
        ticketRestrictionEmail,
        ticketRestrictionContact,
        sharedQuotaError,
        //[End]Bugs #3593 Intermittent quantity restriction error encountered
        // [End] CR#2933, #2917  (Quota and Ticket Quantity Restriction)
        
        // Added for VMS - Errors for vouchers
        voucherConfigConflictSpanError,
        voucherGenericError,
        voucherValidationError,
        voucherIssueConfigNotFound,
        voucherIssueInvalidDate,
        voucherIssueInvalidStatus,
        voucherIssueExceedQuota,
        voucherIssueExceedSerialRange,
        voucherIssueError,
        voucherIssueOverrideParams,
        voucherIssueOverrideDateRange,
        voucherIssueOverrideDurationError,
        voucherRedeemInvalidTxnRef,
		voucherRedeemOtherVoucherInvalid,
        voucherRedeemValidationError,
        voucherRedeemNotFound,
        voucherRedeemRedeemed,
        voucherRedeemReturned,
        voucherRedeemInvalidated,
        voucherRedeemInactive,
        voucherRedeemSettled,
        voucherRedeemCondition,
        voucherRedeemValidity,
        voucherRedeemExpired,
        voucherRedeemProcessError,
        voucherRedeemError,
        voucherRedeemPaymentError,
        voucherRedeemPartialError,
        voucherInquiryError,
        voucherReturnNotFound,
        voucherReturnRedeemed,
        voucherReturnReturned,
        voucherReturnInvalidated,
        voucherReturnSettled,
        voucherReturnExpired,
        voucherReturnError,
        voucherReturnRollbackError,
        voucherRollbackNotFound,
        voucherRollbackInvalidStatus,
        voucherRollbackInvalidAction,
        // [End] For VMS
        
        //[START]  ST-7909 membership tier validation
        membershipTierValidationFail,
        // [END] ST-7909
        //[START]  new CMS API
        HTTPCLIENTNOTFOUND_EXCEPTION,
        HTTPCLIENTERROR_EXCEPTION,
        RESTCLIENT_EXCEPTION,
        CMSAPI_GENERICERROR,
        UNPROCESSABLE_ENTITY_EXCEPTION
        //[END]  new CMS API
        
        ;
    }

    private ExceptionType type;

    /**
     * Gets the type.
     *
     * @return the type
     */
    public ExceptionType getType() {
        return type;
    }

    /**
     * Default Constructor.
     */
    public CoreException() {
        if (errorLogger.isDebugEnabled()) {
            errorLogger.debug((this.getMessage() != null) ? this.getMessage() + ": " + this.getStackTrace()[0] : this.getStackTrace());
        }
    }

    /**
     * The Constructor.
     *
     * @param message the message
     */
    public CoreException(String message) {
        super(message);
        if (errorLogger.isDebugEnabled()) {
            errorLogger.debug((this.getMessage() != null) ? this.getMessage() + ": " + this.getStackTrace()[0] : this.getStackTrace());
        }
    }

    /**
     * The Constructor.
     *
     * @param cause the cause
     */
    public CoreException(Throwable cause) {
        super(cause.getMessage(), cause);
        if (errorLogger.isDebugEnabled() && !(cause instanceof CoreException)) {
            errorLogger.debug((this.getMessage() != null) ? this.getMessage() + ": " + this.getStackTrace()[0] : this.getStackTrace());
        }
    }

    /**
     * The Constructor.
     *
     * @param message the message
     * @param cause the cause
     */
    public CoreException(String message, Throwable cause) {
        super(message, cause);
        if (errorLogger.isDebugEnabled() && !(cause instanceof CoreException)) {
            errorLogger.debug((this.getMessage() != null) ? this.getMessage() + ": " + this.getStackTrace()[0] : this.getStackTrace());
        }
    }

    /**
     * Instantiates a new core exception.
     *
     * @param message the message
     * @param cause the cause
     * @param type the type
     */
    public CoreException(String message, Throwable cause, ExceptionType type) {
        super(message, cause);
        this.type = type;
    }

    /**
     * Instantiates a new core exception.
     *
     * @param message the message
     * @param type the type
     */
    public CoreException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    /**
     * Instantiates a new core exception.
     *
     * @param cause the cause
     * @param type the type
     */
    public CoreException(Throwable cause, ExceptionType type) {
        super(cause);
        this.type = type;
    }
}
