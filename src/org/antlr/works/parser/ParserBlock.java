package org.antlr.works.parser;

import java.util.ArrayList;
import java.util.List;

/*

[The "BSD licence"]
Copyright (c) 2005 Jean Bovet
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1. Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.
3. The name of the author may not be used to endorse or promote products
derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

*/

public class ParserBlock {

    public String name;
    public Token start;
    public Token end;

    public boolean isTokenBlock = false;
    public boolean isOptionsBlock = false;

    public List internalTokens;
    public String tokenVocab;

    public ParserBlock(String name, Token start, Token end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public List getInternalTokens() {
        if(internalTokens == null) {
            internalTokens = new ParseInternalTokens().parseTokens();
        }
        return internalTokens;
    }

    public void parseOptionsBlock() {
        List tokens = getInternalTokens();
        for(int index=0; index<tokens.size(); index++) {
            Token t = (Token)tokens.get(index);
            if(t.getAttribute().equals("tokenVocab") && index+1<tokens.size()) {
                t = (Token) tokens.get(index+1);
                tokenVocab = t.getAttribute();
            }
        }
    }

    public String getTokenVocab() {
        return tokenVocab;
    }

    public class ParseInternalTokens extends AbstractParser {

        public ParseInternalTokens() {
        }

        public List parseTokens() {
            List tokens = new ArrayList();
            String content = end.getAttribute();
            init(content.substring(1, content.length()));
            while(nextToken()) {
                if(T(0).type == Lexer.TOKEN_ID && T(1) != null) {
                    if(T(1).getAttribute().equals("=")) {
                        tokens.add(T(0));
                    } else if(T(1).getAttribute().equals(";")) {
                        tokens.add(T(0));
                    }
                }
            }
            return tokens;
        }

    }
}
