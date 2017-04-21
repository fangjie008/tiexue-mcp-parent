package com.tiexue.mcp.core.service;

public interface IWxCallbackService {
	Boolean checkSignature(String signature, String timestamp, String nonce);
}
