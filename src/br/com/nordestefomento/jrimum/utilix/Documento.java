/*
 * Copyright 2007, JMatryx Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">
 * http://www.apache.org/licenses/LICENSE-2.0 </a>
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright 2007, Grupo JMatryx
 * 
 * Licenciado sob a licença da Apache, versão 2.0 (a “licença”); você não pode
 * usar este arquivo exceto na conformidade com a licença. Você pode obter uma
 * cópia da licença em
 * 
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">
 * http://www.apache.org/licenses/LICENSE-2.0 </a>
 * 
 * A menos que seja requerido pela aplicação da lei ou esteja de acordo com a
 * escrita, o software distribuído sob esta licença é distribuído “COMO É”
 * BASE,SEM AS GARANTIAS OU às CONDIÇÕES DO TIPO, expresso ou implicado. Veja a
 * licença para as permissões sobre a línguagem específica e limitações quando
 * sob licença.
 * 
 * 
 * Created at / Criado em : 02/04/2007 - 17:24:55
 * 
 */
package br.com.nordestefomento.jrimum.utilix;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.nordestefomento.jrimum.ACurbitaObject;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 * Descrição:
 * 
 * 
 * @author Gabriel Guimarães
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto 
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento Mercantil</a>
 * 
 * @since JMatryx 1.0
 * 
 * @version 1.0
 */
public class Documento extends ACurbitaObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6824599737676504544L;

	protected static Logger log = Logger.getLogger(Documento.class);

	protected Document documento;
	
	protected PdfWriter writer;
	
	protected PdfContentByte contentByte;
	
	protected ByteArrayOutputStream arquivoByteStream;

	/**
	 * 
	 */
	protected Documento() {
		super();
	}

	protected Documento (String tituloDoc, String autorDoc) throws DocumentException {

		if(log.isTraceEnabled())
			log.trace("Instanciando Documento, titulo: "+ tituloDoc + "; autor: "+autorDoc);
		
		
		Document document = new Document(PageSize.A4);
		document.addAuthor(autorDoc);
		document.addTitle(tituloDoc);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	
		documento = document;
		writer = PdfWriter.getInstance(document,out);
		arquivoByteStream = out;
		
		if(log.isTraceEnabled())
			log.trace("Documento Instanciado: "+ this);
		
	}
	
	public File getFile(String pathName)throws IllegalArgumentException, IOException{
		
		File file = null;
		
		if(StringUtils.isNotBlank(pathName)){
			
			file = new File(pathName);
			
			FileOutputStream fout = new FileOutputStream(file);
			
			fout.write(arquivoByteStream.toByteArray());
			
			fout.flush();
			fout.close();
			
		}else{
			IllegalArgumentException e = new IllegalArgumentException("Path File [ "+pathName+" ] não permitido !");
			log.error("Valor Não Permitido!",e);
			throw e;
		}
		
		return file;
	}
	
	public ByteArrayOutputStream getStream() throws IOException{
		
		ByteArrayOutputStream outPuted = null;
		
		outPuted = new ByteArrayOutputStream();
		
		outPuted.write(arquivoByteStream.toByteArray());
		
		return outPuted;
	}
	
	public byte[] getBytes(){
		return arquivoByteStream.toByteArray();
	}

}